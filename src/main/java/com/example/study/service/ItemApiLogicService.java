package com.example.study.service;


import com.example.study.controller.CrudController;
import com.example.study.ifs.CrudInterFace;
import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.PartnerRepository;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;

@Service
//public class ItemApiLogicService implements CrudInterFace<ItemApiRequest, ItemApiResponse> {
//public class ItemApiLogicService extends CrudController<ItemApiRequest, ItemApiResponse> {
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private PartnerRepository partnerRepository;     //partner 객체를 찾아오기 위하여 선언

    //private Itemrepository itemRepository         //baseService에서 상속받으므로 필요X

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();        //나중에 body null값 체크해줘야함 optional

        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();

        Item newItem = baseRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(long id) {
        return baseRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(()->{
                    return Header.ERROR("데이터 없음.");
                });


    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(entityItem->{
                    entityItem.setStatus(body.getStatus())
                            .setName(body.getName())
                            .setTitle(body.getTitle())
                            .setContent(body.getContent())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt());
                    return entityItem;
                })
                .map( newEntityItem-> baseRepository.save(newEntityItem) )      //저장된 entity가 다음 map으로 반환
                .map(item->response(item))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                    .map(item-> {                     //map은 리턴값이 있어야 하기때문
                        baseRepository.delete(item);
                        return Header.OK();
                    })
                    .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    //CRUD 공통 response
    private Header<ItemApiResponse> response(Item item){

        //String statusTitle = item.getStatus().getTitle();   //title을 내려줄수있음

        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(body);
    }
}
