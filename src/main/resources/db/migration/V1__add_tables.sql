CREATE TABLE admins
(
    admin_id SERIAL PRIMARY KEY,
    login CHARACTER VARYING(90),
    password CHARACTER VARYING(90),
    full_name CHARACTER VARYING(90)
);

CREATE TABLE users
(
    user_id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(90),
    phone CHARACTER VARYING(15),
    age INTEGER,
    district CHARACTER VARYING(90),
    street  CHARACTER VARYING(90),
    house CHARACTER VARYING(90),
    flat CHARACTER VARYING(90),
    login CHARACTER VARYING(90),
    password CHARACTER VARYING(90)
);

CREATE TABLE volunteer
(
    volunteer_id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(90),
    age INTEGER,
    experience INTEGER,
    tg_chat CHARACTER VARYING(90),
    phone CHARACTER VARYING(15),
    gender CHARACTER VARYING(15),
    employed_date DATE,
    district CHARACTER VARYING(90),
    street  CHARACTER VARYING(90),
    house CHARACTER VARYING(90),
    flat CHARACTER VARYING(90)
);

CREATE TABLE feed_back
(
    feed_id SERIAL PRIMARY KEY,
    read_date DATE,
    text CHARACTER VARYING(500),
    send_date DATE,
    is_read BOOLEAN,
    is_answered BOOLEAN,
    answer_text CHARACTER VARYING(500),
    answer_date CHARACTER VARYING(500),
    volunteer_id INT,
    CONSTRAINT fk_volunteer
        FOREIGN KEY(volunteer_id)
            REFERENCES volunteer(volunteer_id)
);

CREATE TABLE settings
(
    setting_id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(90),
    value CHARACTER VARYING(90)
);

CREATE TABLE task
(
    task_id SERIAL PRIMARY KEY,
    is_read BOOLEAN,
    is_process BOOLEAN,
    completion_date DATE,
    start_hour CHARACTER VARYING(90),
    end_hour CHARACTER VARYING(90),
    description CHARACTER VARYING(90),
    checking_code CHARACTER VARYING(90),
    volunteer_id INT,
    user_id INT,
    CONSTRAINT fk_volunteer
        FOREIGN KEY(volunteer_id)
            REFERENCES volunteer(volunteer_id),
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
            REFERENCES users(user_id)
);

CREATE TABLE sub_task
(
    sub_id SERIAL PRIMARY KEY,
    title CHARACTER VARYING(90),
    type CHARACTER VARYING(90),
    task_id INT,
    CONSTRAINT fk_task
        FOREIGN KEY(task_id)
            REFERENCES task(task_id)
);

CREATE TABLE command
(
    setting_id SERIAL PRIMARY KEY,
    text CHARACTER VARYING(90),
    description CHARACTER VARYING(90),
    is_active BOOLEAN
);

CREATE TABLE dialog_context
(
    sub_id SERIAL PRIMARY KEY,
    chat CHARACTER VARYING(90),
    dialog_type CHARACTER VARYING(90),
    dialog_step CHARACTER VARYING(90),
    pervious_message CHARACTER VARYING(90),
    is_register BOOLEAN,
    register_num CHARACTER VARYING(90),
    select_district CHARACTER VARYING(90)
);