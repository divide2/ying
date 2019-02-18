package com.ying.mine.service.impl;

import com.ying.auth.service.AclService;
import com.ying.auth.service.UserService;
import com.ying.auth.vo.UserGroupVO;
import com.ying.core.er.Loginer;
import com.ying.friend.dto.MessageDTO;
import com.ying.friend.query.MessageQuery;
import com.ying.friend.service.ChatService;
import com.ying.friend.service.FriendService;
import com.ying.friend.service.MessageService;
import com.ying.friend.vo.ChatVO;
import com.ying.friend.vo.FriendVO;
import com.ying.friend.vo.MessageVO;
import com.ying.mine.service.MineService;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.service.OrderService;
import com.ying.order.service.PurchaseOrderService;
import com.ying.order.service.SellOrderService;
import com.ying.order.vo.OrderVO;
import com.ying.product.model.Warehouse;
import com.ying.product.query.StockQuery;
import com.ying.product.service.ProductService;
import com.ying.product.service.StockService;
import com.ying.product.service.WarehouseService;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/12/10
 */
@Service
public class MineServiceImpl implements MineService {

    private final FriendService friendService;
    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;
    private final AclService aclService;

    public MineServiceImpl(
                           FriendService friendService,
                           ChatService chatService,
                           MessageService messageService,
                           UserService userService,
                           AclService aclService) {
        this.friendService = friendService;
        this.chatService = chatService;
        this.messageService = messageService;
        this.userService = userService;
        this.aclService = aclService;
    }

    @Override
    public Page<MessageVO> findMessage(Integer toUserId, MessageQuery query, Pageable pageable) {
        return messageService.findChatMessage(Loginer.userId(), toUserId, query, pageable);
    }

    @Override
    public void sendMessage(MessageDTO messageDTO) {
        messageDTO.setFromId(Loginer.userId());
        messageService.sendMessage(messageDTO);
    }

    @Override
    public List<ChatVO> listChat() {
        return chatService.listByUser(Loginer.userId());
    }



    @Override
    public List<FriendVO> listFriends() {
        return friendService.listByFromId(Loginer.userId());
    }

    @Override
    public FriendVO getFriend(Integer friendId) {
        return friendService.getVO(Loginer.userId(), friendId);
    }


    @Override
    public List<UserGroupVO> listUserGroup() {
        return userService.listUserGroup(Loginer.userId());
    }

    @Override
    public List<String> listAuthorities(String groupId) {
        return aclService.listAuthorities(Loginer.userId(), groupId);
    }
}
