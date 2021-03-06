SELECT 
    message_id, sender_id, target_id,
    message_header, message_text,
    message_read,
    message_timestamp
FROM messages
WHERE
    (
        target_id = ?
    )
ORDER BY
    message_timestamp