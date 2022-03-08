package com.microservices.demo.elastic.query.service.model;

import com.microservices.demo.elastic.query.service.common.model.ElasticQueryServiceResponseModel;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticQueryServiceResponseModelV2 extends RepresentationModel<ElasticQueryServiceResponseModel> {
    private Long id;
    private Long userId;
    private String text;
    private String textV2;
}
