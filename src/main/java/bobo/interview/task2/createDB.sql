create table user(
    id bigint primary key autoincrement,
    nvarchar(100)



);

create table order(

    foreign key (user_id) references user()

)