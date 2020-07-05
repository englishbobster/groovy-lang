def text = """
Ten to doomsday, moving fast...
Heads up! Mind that blast.
No time to sleep, it's Def.Con.One.
Can't get no sleep as the ticking ticks on,
No time for fear, it's Def.Con.One,
No time to eat but get me some
Big Mac, fries to go...
Big Mac, fries to go...
Get me Big Mac, fries to go...
Get me Big Mac, get me fries to go...
Watchman!
We love you all...
"""

def words = text.tokenize()
def wordFreq = [:]

//build map of key:word value:freq
words.each { word ->
  wordFreq[word] = wordFreq.get(word, 0) + 1
}

//just get the keys (get the words) and sort ascending
def wordList = wordFreq.keySet().toList()
wordList.sort {wordFreq[it]}

//so wordList should contain everything in words
assert wordList.disjoint(words) == false

def statistic = "\n"
//for the last 5 (5 highest frequency) build a results table
wordList[-1..-5].each { word ->
  statistic += word.padLeft(12) + ':'
  statistic += wordFreq[word] + "\n"
}

println(statistic)
