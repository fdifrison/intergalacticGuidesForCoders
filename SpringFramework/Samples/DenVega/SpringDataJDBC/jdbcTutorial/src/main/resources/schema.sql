-- author
create table IF NOT EXISTS Author
(
    id         serial primary key,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    email      varchar(255) not null,
    username   varchar(100) not null
);


-- post
create table IF NOT EXISTS Post
(
    id           serial primary key,
    version      int, -- specific to spring data jdbc
    title        varchar(255) not null,
    content      text         not null,
    published_on timestamp    not null,
    updated_on   timestamp,
    author       int,
    foreign key (author) references Author (id)
);

-- comment
create table IF NOT EXISTS Comment
(
    post         int          not null, -- we need a column with the name of the aggregate root (Post)
    name         varchar(100) not null,
    content      text         not null,
    published_on timestamp    not null,
    updated_on   timestamp,
    foreign key (post) references Post (id)
);