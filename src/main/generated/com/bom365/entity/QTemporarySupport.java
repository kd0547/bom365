package com.bom365.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTemporarySupport is a Querydsl query type for TemporarySupport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemporarySupport extends EntityPathBase<TemporarySupport> {

    private static final long serialVersionUID = -1022352486L;

    public static final QTemporarySupport temporarySupport = new QTemporarySupport("temporarySupport");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath aid = createString("aid");

    public final DateTimePath<java.time.LocalDateTime> approved_at = createDateTime("approved_at", java.time.LocalDateTime.class);

    public final StringPath cid = createString("cid");

    public final DateTimePath<java.time.LocalDateTime> created_at = createDateTime("created_at", java.time.LocalDateTime.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath item_code = createString("item_code");

    public final StringPath item_name = createString("item_name");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath partner_order_id = createString("partner_order_id");

    public final StringPath partner_user_id = createString("partner_user_id");

    public final StringPath payload = createString("payload");

    public final StringPath payment_method_type = createString("payment_method_type");

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final StringPath sid = createString("sid");

    public final StringPath tid = createString("tid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QTemporarySupport(String variable) {
        super(TemporarySupport.class, forVariable(variable));
    }

    public QTemporarySupport(Path<? extends TemporarySupport> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTemporarySupport(PathMetadata metadata) {
        super(TemporarySupport.class, metadata);
    }

}

