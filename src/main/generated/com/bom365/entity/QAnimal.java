package com.bom365.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnimal is a Querydsl query type for Animal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnimal extends EntityPathBase<Animal> {

    private static final long serialVersionUID = -585242536L;

    public static final QAnimal animal = new QAnimal("animal");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Double> animalAge = createNumber("animalAge", Double.class);

    public final EnumPath<com.bom365.constant.Gender> animalGender = createEnum("animalGender", com.bom365.constant.Gender.class);

    public final StringPath animalImage = createString("animalImage");

    public final StringPath animalName = createString("animalName");

    public final StringPath animalSpecies = createString("animalSpecies");

    public final StringPath animalType = createString("animalType");

    public final NumberPath<Double> animalWeight = createNumber("animalWeight", Double.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath Neutering = createString("Neutering");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QAnimal(String variable) {
        super(Animal.class, forVariable(variable));
    }

    public QAnimal(Path<? extends Animal> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnimal(PathMetadata metadata) {
        super(Animal.class, metadata);
    }

}

