
CREATE TABLE IF NOT EXISTS `usuario` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL,
    UNIQUE (`username`),
    UNIQUE (`email`)
);

CREATE TABLE IF NOT EXISTS `mood` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `humor` INT NOT NULL,
    `fome` INT NOT NULL,
    `tedio` INT NOT NULL,
    `fisica` INT NOT NULL,
    `emocional` INT NOT NULL,
    `falar` INT NOT NULL,
    `ouvir` INT NOT NULL,
    `companhia` INT NOT NULL,
    `recado` TEXT,
    `timestamp` DATETIME NOT NULL,
    CONSTRAINT `fk_mood_user` FOREIGN KEY (`user_id`) REFERENCES `usuario`(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `friendship` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `friend_id` BIGINT NOT NULL,
    `status` VARCHAR(50) NOT NULL,  -- 'pending', 'accepted', 'rejected'
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME,
    CONSTRAINT `fk_friendship_user` FOREIGN KEY (`user_id`) REFERENCES `usuario`(`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_friendship_friend` FOREIGN KEY (`friend_id`) REFERENCES `usuario`(`id`) ON DELETE CASCADE,
    UNIQUE (`user_id`, `friend_id`)
);
