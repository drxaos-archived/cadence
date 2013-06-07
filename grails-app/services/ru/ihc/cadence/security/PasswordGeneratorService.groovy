package ru.ihc.cadence.security

class PasswordGeneratorService {

    def makeSalt() {
        def symbols = [].with {
            it.addAll('0'..'9')
            it.addAll('a'..'z')
            it.addAll('A'..'Z')
            return it
        }
        Collections.shuffle(symbols)
        return symbols.subList(0, 7).join("")
    }
}
