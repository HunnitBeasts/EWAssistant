package com.hunnit_beasts.EWAssistant.service.modules;

public interface CrudService<C, R, U, K> {

    R create(C dto);
    R read(K id);
    R update(U dto);
    K delete(K id);
}
