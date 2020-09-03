create table opros (
  id bigserial not null,
  title varchar(50) not null,
  createdat timestamp without time zone not null,
  endat timestamp without time zone not null,
  active boolean not null,
  primary key (id)
);

create table vopros (
  id bigserial not null,
  opros_id bigint not null references opros (id),
  poradok bigint not null,
  description text not null,
  primary key (id)
);
