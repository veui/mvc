drop table Client;
drop table Department;

create table Client(
	client_id number,
	username varchar2(20) not null,
    password varchar2(50) not null,
    last_name varchar2(20),
    first_name varchar2(20),
    email varchar2(20),
    phone number(10),
	constraint client_pk primary key(client_id),
	constraint client_username_unq unique(username),
	constraint client_email_unq unique(email)
);

create table Department(
	department_id number,
	title varchar2(50),
	constraint department_pk primary key(department_id)
);

create table Specialty(
	specialty_id number,
	title varchar2(50),
	department_id number,
	constraint specialty_pk primary key(specialty_id),
	constraint specialty_department_id_fk
		foreign key(department_id)
		references Department(department_id)
		on delete cascade
);


create table Item(
	item_id number,
	item varchar2(50),
	price number,
	specialty_id number,
	constraint item_id_pk primary key(item_id),
	constraint item_specialty_id_fk
		foreign key(specialty_id)
		references Specialty(specialty_id)
		on delete cascade
);

create table Order_(
	order_id number,
    order_date date,
    amount number,
    cost number,
    client_id number,
    item_id number,
	constraint order_pk primary key(order_id),
	constraint order_client_id_fk
		foreign key(client_id)
		references Client(client_id)
		on delete cascade,
	constraint order_item_id_fk
		foreign key(item_id)
		references Item(item_id)
		on delete cascade
);

alter table Specialty
    add parent_id number;

alter table Client
    MODIFY phone number(20);

commit;
