-- Criação da tabela usuario_admin
CREATE TABLE usuario_admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    UNIQUE (username),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Criação da tabela usuario
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    UNIQUE (username),
    UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- Criação da tabela usuario_password
CREATE TABLE usuario_password (
    user_id BIGINT NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    CONSTRAINT fk_user_password FOREIGN KEY (user_id) REFERENCES usuario(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Criação da tabela mood
CREATE TABLE mood (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    humor INT NOT NULL,
    fome INT NOT NULL,
    tedio INT NOT NULL,
    fisica INT NOT NULL,
    emocional INT NOT NULL,
    falar INT NOT NULL,
    ouvir INT NOT NULL,
    companhia INT NOT NULL,
    recado TEXT,
    timestamp DATETIME NOT NULL,
    CONSTRAINT fk_mood_user FOREIGN KEY (user_id) REFERENCES usuario(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Criação da tabela friendship
CREATE TABLE friendship (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    friend_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,  -- 'pending', 'accepted', 'rejected'
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    CONSTRAINT fk_friendship_user FOREIGN KEY (user_id) REFERENCES usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_friendship_friend FOREIGN KEY (friend_id) REFERENCES usuario(id) ON DELETE CASCADE,
    UNIQUE (user_id, friend_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
