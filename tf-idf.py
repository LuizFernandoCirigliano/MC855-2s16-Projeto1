# encoding: utf-8

import pickle

def save_DF_as_pickle_dict():
    df_dict = {}
    with open('InputFiles/Corpus/df.txt', 'r', encoding='utf-8') as f:
        for line in f.readlines():
            words = line.split()
            # print words
            try:
                df_dict[words[0]] = int(words[1])
            except:
                continue

    pickle.dump(df_dict, open('InputFiles/Corpus/df.pickle', 'wb'))

def main():
    save_DF_as_pickle_dict()

if __name__ == '__main__':
    main()