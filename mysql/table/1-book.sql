CREATE TABLE book
(
    isbn       VARCHAR(13)  NOT NULL COMMENT 'ISBN',
    title      VARCHAR(100) NOT NULL COMMENT 'タイトル',
    author     VARCHAR(100) NOT NULL COMMENT '著者',
    price      INT          NOT NULL COMMENT '価格',
    created_at DATETIME     NOT NULL COMMENT '登録日時',
    updated_at DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '編集日時',
    PRIMARY KEY (isbn)
) ENGINE = InnoDB