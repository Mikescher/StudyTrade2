SELECT 
    passwordhash,
    user_id, forename, lastname, nickname, city, university, studydirection, mail, activated
FROM users 
WHERE user_id = ? 
LIMIT 1