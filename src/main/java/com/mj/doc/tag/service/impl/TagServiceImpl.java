package com.mj.doc.tag.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.doc.tag.model.Tag;
import com.mj.doc.tag.repo.TagRepository;
import com.mj.doc.tag.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@Service
public class TagServiceImpl extends SimpleBasicServiceImpl<Tag, Integer, TagRepository> implements TagService {



}
