package com.divide2.friend.repo.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.divide2.core.basic.custom.impl.SimpleBasicCustomRepositoryImpl;
import com.divide2.friend.model.Message;
import com.divide2.friend.model.QMessage;
import com.divide2.friend.repo.custom.MessageRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

/**
 * @author bvvy
 * @date 2019/1/2
 */
public class MessageRepositoryImpl extends SimpleBasicCustomRepositoryImpl implements MessageRepositoryCustom {

    private final QMessage m = QMessage.message;

    public MessageRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
    @Override
    public Page<Message> findChatMessage(Integer fromId, Integer toId, Pageable pageable) {
        JPAQuery<Message> query = createQuery();
        query.from(m).where(

                Expressions.ONE.eq(Expressions.ONE)
                .and(m.fromId.eq(fromId).or(m.fromId.eq(toId))).and(m.toId.eq(fromId).or(m.toId.eq(toId)))
        ).orderBy(new OrderSpecifier<>(Order.DESC, Expressions.stringPath("createTime")));
        return findPage(query, pageable);
    }
}
