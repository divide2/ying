package com.ying.basis.tag.service.impl;

import com.ying.basis.tag.model.Tag;
import com.ying.basis.tag.repo.TagRepository;
import com.ying.basis.tag.service.TagService;
import com.ying.basis.tag.vo.TagVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@Service
public class TagServiceImpl extends SimpleBasicServiceImpl<Tag, Integer, TagRepository> implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag add(Tag tag) {
        Tag tTag = tagRepository.findByName(tag.getName());
        if (tTag != null) {
            tTag.setCounter(tTag.getCounter() + 1);
            return tagRepository.save(tTag);
        } else {
            tag.setCounter(1);
            return tagRepository.save(tag);
        }
    }

    @Override
    public void add(List<String> tagNames) {
        tagNames.forEach(name -> {
            Tag tag = new Tag();
            tag.setName(name.trim());
            this.add(tag);
        });
    }

    @Override
    public List<TagVO> findByCount(Integer count) {
        Pageable pageable = PageRequest.of(0, count);
        return tagRepository.findAll(pageable).map(TagVO::of).getContent();
    }
}
