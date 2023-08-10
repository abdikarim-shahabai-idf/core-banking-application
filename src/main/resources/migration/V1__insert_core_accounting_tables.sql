create type finance_type_enum as ENUM ('CREDIT', 'DEPOSIT');
create type agreement_status as ENUM ('ACTIVE', 'CLOSED', 'PENDING');

CREATE TABLE agreement
(
    id             INT8 PRIMARY KEY  NOT NULL,
    status         agreement_status  NOT NULL,
    customer_id    INT8              NOT NULL,
    date_requested TIMESTAMP         NOT NULL,
    start_date     DATE              NOT NULL,
    end_date       DATE,
    finance_type   finance_type_enum NOT NULL,
    amount         DECIMAL           NOT NULL,
    interest_rate  DECIMAL           NOT NULL
);

CREATE TABLE account
(
    id           INT8     PRIMARY KEY NOT NULL,
    description  VARCHAR(256)       NOT NULL,
    number       VARCHAR(20) UNIQUE NOT NULL,
    agreement_id INT8 NULL,
    unblocked    BOOLEAN            NOT NULL DEFAULT TRUE,
    FOREIGN KEY (agreement_id) REFERENCES agreement (id)
);

CREATE TABLE ca_sign_type
(
    id          INT8 PRIMARY KEY NOT NULL,
    status      varchar(255),
    sign        INT              NOT NULL,
    code        VARCHAR(30)      NOT NULL,
    description VARCHAR(255)     NOT NULL,
    CONSTRAINT ca_sign_type_balance_sign_check CHECK (((sign >= '-1'::integer) AND (sign <= 1))),
    CONSTRAINT ca_sign_uk UNIQUE (code)
);

CREATE TABLE chart_of_ac_type
(
    id           INT8 PRIMARY KEY    NOT NULL,
    status       VARCHAR(255),
    code         VARCHAR(255) UNIQUE NOT NULL,
    description  VARCHAR(500)        NOT NULL,
    sign_type_id INT8,
    FOREIGN KEY (sign_type_id) REFERENCES ca_sign_type (id)
);

create type chart_of_acc_status as ENUM ('ACTIVE', 'ARCHIVED');

CREATE TABLE chart_of_account
(
    id                  INT8 PRIMARY KEY    NOT NULL,
    status              chart_of_acc_status,
    code                VARCHAR(255) UNIQUE NOT NULL,
    description         VARCHAR(255)        NOT NULL,
    in_balance          BOOLEAN NULL,
    is_header           BOOLEAN             NOT NULL,
    parent_ca_id        INT8 NULL,
    chart_of_ac_type_id INT8                NOT NULL,
    FOREIGN KEY (chart_of_ac_type_id) REFERENCES chart_of_ac_type (id)
);

CREATE TABLE account_ca
(
    id                  INT8 NOT NULL,
    status              varchar(255) NULL,
    account_id          INT8             NOT NULL,
    chart_of_account_id INT8             NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account (id),
    FOREIGN KEY (chart_of_account_id) REFERENCES chart_of_account (id),
    CONSTRAINT account_ca_pkey PRIMARY KEY (id)
);

CREATE TABLE doc_type
(
    id   INT8 PRIMARY KEY    NOT NULL,
    code VARCHAR(256) UNIQUE NOT NULL,
    name VARCHAR(256)        NOT NULL
);

CREATE TABLE po_status
(
    id   INT8 PRIMARY KEY    NOT NULL,
    code VARCHAR(256) UNIQUE NOT NULL,
    name VARCHAR(256)
);

CREATE TABLE payment_type
(
    id          INT8    PRIMARY KEY NOT NULL,
    code        VARCHAR(256) UNIQUE NOT NULL,
    description VARCHAR(256)        NOT NULL
);

CREATE TABLE currency
(
    id               INT8 PRIMARY KEY  NOT NULL,
    code             VARCHAR(5) UNIQUE NOT NULL,
    enabled          BOOLEAN           NOT NULL,
    mnemo_code       VARCHAR(3)        NOT NULL,
    presition        INT,
    reverse_rate     numeric(19, 2),
    swift_mnemo_code VARCHAR(3)
);

CREATE TABLE payment
(
    id                   BIGINT PRIMARY KEY NOT NULL,
    amount               DECIMAL            NOT NULL,
    doc_number           VARCHAR(256),
    event_number         VARCHAR(256),
    event_transaction_id INT8,
    purpose              VARCHAR(256)       NOT NULL,
    date_created         DATE               NOT NULL,
    credit_acc_id        INT8               NOT NULL,
    debit_acc_id         INT8               NOT NULL,
    doc_type_id          INT8               NOT NULL,
    po_status_id         INT8               NOT NULL,
    creator_id           INT8               NOT NULL,
    currency_id          INT8               NOT NULL,
    sender_idn           INT                NOT NULL,
    receiver_idn         INT                NOT NULL,
    sender_irs           INT                NOT NULL,
    receiver_irs         INT                NOT NULL,
    sender_seco          INT                NOT NULL,
    receiver_seco        INT                NOT NULL,
    external_id          INT8,
    payment_type_id      INT8               NOT NULL,
    FOREIGN KEY (credit_acc_id) REFERENCES account (id),
    FOREIGN KEY (debit_acc_id) REFERENCES account (id),
    FOREIGN KEY (doc_type_id) REFERENCES doc_type (id),
    FOREIGN KEY (po_status_id) REFERENCES po_status (id),
    FOREIGN KEY (payment_type_id) REFERENCES payment_type (id),
    FOREIGN KEY (currency_id) REFERENCES currency (id)
);

CREATE TABLE turnover
(
    id            INT8 PRIMARY KEY NOT NULL,
    amount_credit numeric(19, 2),
    amount_debit  numeric(19, 2),
    balance       INT8             NOT NULL,
    value_date    DATE             NOT NULL,
    account_id    INT8             NOT NULL,
    currency_id   INT8             NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account (id),
    CONSTRAINT turnover_acc_curr_vd_unq UNIQUE (account_id, currency_id, value_date)
);

CREATE TABLE turnover_payment
(
    id                      INT8 PRIMARY KEY NOT NULL,
    date                    timestamp        NOT NULL,
    payment_id              INT8             NOT NULL,
    turnover_of_accounts_id INT8             NOT NULL,
    FOREIGN KEY (payment_id) REFERENCES payment (id),
    FOREIGN KEY (turnover_of_accounts_id) REFERENCES turnover (id)
);

CREATE TABLE account_currency
(
    account_id  INT8 NOT NULL,
    currency_id INT8 NOT NULL,
    CONSTRAINT account_currency_pkey PRIMARY KEY (account_id, currency_id),
    FOREIGN KEY (account_id) REFERENCES account (id),
    FOREIGN KEY (currency_id) REFERENCES currency (id)
);