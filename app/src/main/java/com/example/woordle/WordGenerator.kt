package com.example.woordle

import android.app.Activity
import android.widget.Button
import android.content.Context


val wordsArray = arrayOf(
    "ace", "act", "add", "ado", "ads", "adz", "aff", "aft", "age", "ago",
    "aid", "ail", "aim", "air", "ala", "ale", "all", "amp", "and", "ane",
    "ant", "any", "ape", "apt", "arc", "are", "ark", "arm", "art", "ash",
    "ask", "asp", "ass", "ate", "ave", "awe", "awl", "axe", "aye", "ays",
    "bad", "bag", "bah", "ban", "bar", "bat", "bay", "bed", "bee", "beg",
    "bel", "ben", "bet", "bey", "bib", "bid", "big", "bin", "bit", "biz",
    "boa", "bob", "bog", "boo", "bop", "bow", "box", "boy", "bra", "bub",
    "bud", "bug", "bum", "bun", "bus", "but", "buy", "bye", "bys", "cab",
    "cad", "cam", "can", "cap", "car", "cat", "caw", "cay", "cee", "cel",
    "chi", "cis", "cob", "cod", "cog", "col", "con", "coo", "cop", "cos",
    "cot", "cow", "cox", "coy", "coz", "cru", "cry", "cub", "cue", "cum",
    "cup", "cur", "cut", "cwm", "dab", "dad", "dag", "dah", "dak", "dal",
    "dam", "dan", "dap", "daw", "day", "deb", "dee", "def", "del", "den",
    "dev", "dew", "dex", "dey", "dib", "did", "die", "dig", "dim", "din",
    "dip", "dis", "dit", "div", "doc", "doe", "dog", "dol", "dom", "don",
    "doo", "dop", "dor", "dos", "dot", "dow", "dry", "dub", "dud", "due",
    "dug", "duh", "dui", "dun", "duo", "dup", "dux", "dye", "ear", "eat",
    "eau", "ebb", "ecu", "edh", "eds", "eek", "eel", "eff", "efs", "eft",
    "egg", "ego", "eke", "eld", "elf", "elk", "ell", "elm", "els", "eme",
    "ems", "emu", "end", "eng", "ens", "eon", "era", "ere", "erg", "ern",
    "err", "ers", "ess", "eta", "eth", "eve", "ewe", "eye", "fab", "fad",
    "fag", "fan", "far", "fas", "fat", "fax", "fay", "fed", "fee")



class WordGenerator {

    var AnswerArray = CharArray(3);
    var emptyCharArray = CharArray(3)

    constructor()
        {
            val randomIndex = (0 until wordsArray.size).random()
            val word = wordsArray[randomIndex].toString()
            AnswerArray = word.toCharArray();

        }

    fun GetEmpty() : CharArray
    {
       return emptyCharArray
    }


        fun CheckLetter(letter: Char) : Int
            {
                  for(i in AnswerArray.indices)
                  {
                      if(AnswerArray[i] == letter)
                      {
                          emptyCharArray[i] = AnswerArray[i];
                          AnswerArray[i] = ' ';

                          return i
                      }
                  }
                return -1
            }

    fun populateBlocks(): CharArray {
        val temp = CharArray(9)

        // Shuffle the indices to populate the temp array with the AnswerArray values
        val indices = (0 until temp.size).shuffled()
        for (i in 0 until AnswerArray.size) {
            temp[indices[i]] = AnswerArray[i]
        }

        // Fill any remaining empty slots with random characters
        for (i in temp.indices) {
            if (temp[i] == '\u0000') {
                val randomChar = ('a'..'z').random()
                temp[i] = randomChar
            }
        }

        return temp
    }

    fun PopulateButtons(IDs : IntArray, context: Context)

        {
            val myArray = populateBlocks()
            var Num = 0;
        for (id in IDs)
        {
           var button = (context as Activity).findViewById<Button>(id)
            button.text = myArray[Num].toString()
            Num += 1;

        }
        }
}