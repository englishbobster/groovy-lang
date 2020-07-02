def testSentence = 'Two households, both alike in dignity,\n' +
    'In fair Verona, where we lay our scene,\n' +
    'From ancient grudge break to new mutiny,\n' +
    'Where civil blood makes civil hands unclean.\n' +
    'From forth the fatal loins of these two foes\n' +
    'A pair of star-cross\'d lovers take their life;\n' +
    'Whose misadventured piteous overthrows\n' +
    'Do with their death bury their parents\' strife.\n' +
    'The fearful passage of their death-mark\'d love,\n' +
    'And the continuance of their parents\' rage,\n' +
    'Which, but their children\'s end, nought could remove,\n' +
    'Is now the two hours\' traffic of our stage;\n' +
    'The which if you with patient ears attend,\n' +
    'What here shall miss, our toil shall strive to mend.'

//count all "ours"
def finderOur = (testSentence =~ /our/)
def countOur = 0
finderOur.findAll { it ->
  countOur++
}
println("Number of 'ours': $countOur")

//find all words ending in s
def regex = /\b\w*s\b/
def finderEndingS = (testSentence =~ regex)
finderEndingS.each {
  match -> print(match + ' ')
}

//the matcher as an array
println('****' + finderEndingS[0] + '****')
assert finderEndingS.size() ==  16
println('****' + finderEndingS[4..8] + '****')

//and parallel assignment (a bit python like?)...amazing
def (first, second, third) = finderEndingS[0..2]
assert first == 'households'
assert second == 'makes'
assert third == 'hands'

//replace all words ending in s
println(testSentence.replaceAll(regex, "bollox"))

//matcher with and without groupings
def withOutGroups = ('a:1 b:2 c:3' =~ /\S+:\S+/)
def groups = ('a:1 b:2 c:3' =~ /(\S+):(\S+)/)

assert !withOutGroups.hasGroup()
assert groups.hasGroup()

println(withOutGroups[0])
println(withOutGroups[1])
println(withOutGroups[2])
println(groups[0])
println(groups[1])
println(groups[2])
println(groups[1][2])

//~string precompiled and using regex for classification in groovy
//words that are 4 letters long. Note not =~(find operator) but = ~ (whitespace between)... compiled pattern
def fourletters = ~/\w{4}/
assert fourletters.isCase('work')
//use in
assert 'love' in fourletters
//use switch
switch ('beer') {
  case fourletters: assert true; break
  default: assert false
}
//grep is beautiful
beasts = ['bear', 'tiger' ,'wolf', 'regex']
assert beasts.grep(fourletters) == ['bear', 'wolf']
