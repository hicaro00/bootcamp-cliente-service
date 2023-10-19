package com.lizana.customermicroservice.domain.objetos;

import lombok.Data;

import javax.swing.event.DocumentEvent;
import java.util.Date;
@Data
public abstract class Event<T> {

    private String id;
    private Date date;
    private EventType type;
    private T data;
}
