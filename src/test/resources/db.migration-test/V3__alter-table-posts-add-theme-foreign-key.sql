ALTER TABLE tb_posts
ADD COLUMN theme_id BIGINT NOT NULL,
ADD CONSTRAINT fk_theme_id FOREIGN KEY (theme_id) REFERENCES tb_themes(id);
