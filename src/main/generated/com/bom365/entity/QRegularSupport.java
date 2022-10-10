package com.bom365.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRegularSupport is a Querydsl query type for RegularSupport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegularSupport extends EntityPathBase<RegularSupport> {

    private static final long serialVersionUID = -129650961L;

    public static final QRegularSupport regularSupport = new QRegularSupport("regularSupport");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QRegularSupport(String variable) {
        super(RegularSupport.class, forVariable(variable));
    }

    public QRegularSupport(Path<? extends RegularSupport> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegularSupport(PathMetadata metadata) {
        super(RegularSupport.class, metadata);
    }

}

