CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    author JSONB,
    imageUrl VARCHAR(255),
    text TEXT,
    videoUrl VARCHAR(255),
    location JSONB,
    hasVideo BOOLEAN,
    hasImage BOOLEAN,
    hasLocation BOOLEAN,
    createdAt TIMESTAMP
);