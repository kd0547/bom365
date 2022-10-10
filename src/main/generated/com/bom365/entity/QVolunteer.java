package com.bom365.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVolunteer is a Querydsl query type for Volunteer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVolunteer extends EntityPathBase<Volunteer> {

    private static final long serialVersionUID = -638241874L;

    public static final QVolunteer volunteer = new QVolunteer("volunteer");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath phoneNumber = createString("phoneNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final StringPath supporterId = createString("supporterId");

    public final StringPath supporterName = createString("supporterName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QVolunteer(String variable) {
        super(Volunteer.class, forVariable(variable));
    }

    public QVolunteer(Path<? extends Volunteer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVolunteer(PathMetadata metadata) {
        super(Volunteer.class, metadata);
    }

}

