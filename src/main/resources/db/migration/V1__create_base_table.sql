CREATE TABLE roles
(
    id   int AUTO_INCREMENT primary key,
    name varchar(255)
);

CREATE TABLE users
(
    id       int AUTO_INCREMENT primary key,
    email    varchar(255) unique,
    password varchar(255),
    role_id  int not null,
    username varchar(255),
    foreign key (role_id) references roles (id)
);

CREATE TABLE tasks
(
    id          int AUTO_INCREMENT primary key,
    title       varchar(255),
    description varchar(255),
    status      varchar(20),
    priority    varchar(10),
    user_id     int,
    assignee_id int,
    foreign key (user_id) references users (id),
    foreign key (assignee_id) references users (id)
);

CREATE TABLE comments
(
    id      int AUTO_INCREMENT primary key,
    content varchar(255),
    user_id int,
    task_id int,
    foreign key (user_id) references users (id),
    foreign key (task_id) references tasks (id)
);