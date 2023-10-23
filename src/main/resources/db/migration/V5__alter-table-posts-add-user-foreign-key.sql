ALTER TABLE tb_posts
ADD COLUMN user_id BIGINT NOT NULL;

ALTER TABLE tb_posts
ADD CONSTRAINT fk_user_id
    FOREIGN KEY (user_id)
    REFERENCES tb_users (id);
