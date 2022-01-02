package com.streams.tracker.booking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class TransitPath implements Serializable {

    private List<TransitEdge> transitEdges;

}
