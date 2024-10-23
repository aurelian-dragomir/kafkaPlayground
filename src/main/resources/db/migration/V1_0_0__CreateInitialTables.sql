CREATE TABLE person
             (
                          id   INTEGER generated always AS identity,
                          name VARCHAR2(255) NOT NULL,
                          CONSTRAINT pk_person PRIMARY KEY(id)
             );