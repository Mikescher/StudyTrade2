SELECT 
    passwordhash,
    user_id, forename, lastname, nickname, city, university, studydirection, mail
FROM users 
WHERE nickname LIKE ? 
LIMIT 1