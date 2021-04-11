package com.example.study.ifs;

import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;


//CRUD 를 작성시 실수, 누락 방지를 위한 편의용 인터페이스, generic 으로 만들어서 여러 용도의 api controller가 있을때 만드는 번거로움 해결
public interface CrudInterFace<Req, Res> {

    Header<Res> create(Header<Req> request);            //todo request object 추가

    Header<Res> read(long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);



}
