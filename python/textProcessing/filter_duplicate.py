import nltk
word_data = "The Sky is blue also the ocean is blue also Rainbow has a blue colour."

# First Word tokenization
nltk_tokens = nltk.word_tokenize(word_data)

# Applying Set
no_order = list(set(nltk_tokens))

print(no_order)
