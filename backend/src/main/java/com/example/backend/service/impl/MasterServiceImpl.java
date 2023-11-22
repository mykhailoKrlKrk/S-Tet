package com.example.backend.service.impl;

import com.example.backend.dto.master.MasterDto;
import com.example.backend.dto.master.MasterRequestDto;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.mapper.MasterMapper;
import com.example.backend.model.Master;
import com.example.backend.model.Service;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.MasterRepository;
import com.example.backend.service.MasterService;
import com.example.backend.service.ServicesService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;
    private final MasterMapper masterMapper;
    private final ServicesService servicesService;
    private final CategoryRepository categoryRepository;

    @Override
    public List<MasterDto> getAll(Pageable pageable) {
        return masterRepository.findAll(pageable).stream()
                .map(masterMapper::toDto)
                .toList();
    }

    @Override
    public MasterDto getById(Long id) {
        Master master = masterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Master with id: "
                        + id + " is not exist"));
        return masterMapper.toDto(master);
    }

    @Override
    public List<MasterDto> getMastersByCategory(String category) {
        categoryRepository.findByName(category).orElseThrow(
                () -> new EntityNotFoundException("Can't find category with name: " + category));
        return masterRepository.getMastersByCategory(category).stream()
                .map(masterMapper::toDto)
                .toList();
    }

    @Override
    public MasterDto createMaster(MasterRequestDto requestDto) {
        Master model = masterMapper.toModel(requestDto);
        Service service = servicesService.findById(requestDto.getServiceId());
        model.getServices().add(service);
        return masterMapper.toDto(masterRepository.save(model));
    }

    @Override
    public Master findById(Long id) {
        return masterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find master by id: " + id));
    }

    @Override
    public void delete(Long id) {
        masterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find master by id: " + id));
        masterRepository.deleteById(id);
    }
}
