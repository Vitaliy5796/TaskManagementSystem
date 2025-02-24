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
    id          INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    status      VARCHAR(20) NOT NULL,
    priority    VARCHAR(10) NOT NULL,
    user_id     INT,
    assignee_id INT,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    version     INT DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (assignee_id) REFERENCES users (id)
);

CREATE TABLE comments
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    content    VARCHAR(255) NOT NULL,
    user_id    INT,
    task_id    INT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (task_id) REFERENCES tasks (id)
);
