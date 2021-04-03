package com.example.study.ifs;

import com.example.study.model.network.Header;


//CRUD 를 작성시 실수, 누락 방지를 위한 편의용 인터페이스
public interface CrudInterFace {

    Header create();            //todo request object 추가

    Header read(long id);

    Header update();

    Header delete(Long id);

}
