from wordcloud import WordCloud
import os
import matplotlib.pyplot as plt

for root, dirs, files in os.walk("Input"):
    for name in files:
        f = open(os.path.join(root, name))
        frequencies = []
        for line in f:
            key, value = line.split("\t")
            if key and value:
                value = float(value)
                frequencies.append( (key, value) )
        wc = WordCloud(width=1024, height=800, max_words=100).generate_from_frequencies(frequencies)
        plt.imshow(wc)
        plt.axis("off")
        plt.show()
