def decryptMessage(encryptedMessage, keyPhrase):
    """
    Decrypt an encrypted message using the given key phrase.

    Args:
        encryptedMessage (str): The encrypted message to be decrypted.
        keyPhrase (str): The key phrase used to encrypt the message.

    Returns:
        str: The decrypted message.
    """

    # Split the encrypted message and key phrase into words
    encryptedWords = encryptedMessage.split()
    keyPhraseWords = keyPhrase.split()

    # Check if the number of words in the encrypted message and key phrase match
    if len(encryptedWords) != len(keyPhraseWords):
        raise ValueError("Encrypted message and key phrase must have the same number of words")

    # Decrypt each word of the encrypted message using the corresponding word in the key phrase
    decryptedWords = []
    for i, word in enumerate(encryptedWords):
        keyPhraseWord = keyPhraseWords[-i - 1]

        # Decrypt the word based on the length of the key phrase word
        if len(keyPhraseWord) % 2 == 0:
            # Increase each character of the word by the length of the key phrase word
            decryptedWord = ''
            for char in word:
                decryptedWord += chr(ord(char) + len(keyPhraseWord))
        else:
            # Decrease each character of the word by the length of the key phrase word
            decryptedWord = ''
            for char in word:
                decryptedWord += chr(ord(char) - len(keyPhraseWord))

        # Append the decrypted word to the list
        decryptedWords.append(decryptedWord)

    # Return the decrypted message
    return ' '.join(decryptedWords)




encryptedMessage = "xfkjv pejxf euzx"
keyPhrase = "one orange ball"
decryptedMessage = decryptMessage(encryptedMessage, keyPhrase)
print("Decrypted Message:", decryptedMessage)
