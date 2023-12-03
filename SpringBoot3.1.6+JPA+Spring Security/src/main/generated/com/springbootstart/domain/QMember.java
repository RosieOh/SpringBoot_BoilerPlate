package com.springbootstart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 34699788L;

    public static final QMember member = new QMember("member1");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath del = createBoolean("del");

    public final StringPath email = createString("email");

    public final StringPath mid = createString("mid");

    public final StringPath mname = createString("mname");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath mpw = createString("mpw");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final SetPath<com.springbootstart.constant.MemberRole, EnumPath<com.springbootstart.constant.MemberRole>> roleSet = this.<com.springbootstart.constant.MemberRole, EnumPath<com.springbootstart.constant.MemberRole>>createSet("roleSet", com.springbootstart.constant.MemberRole.class, EnumPath.class, PathInits.DIRECT2);

    public final BooleanPath social = createBoolean("social");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

