Name: Zach Zador
email: zazador@gmail.com
CSID: sakz
UTEID: zaz78

Name: Mike Schiller
email: schillbs@gmail.com
CSID: schiller
UTEID: mds3428

Entropy for one letter symbols: 1.9927064525511913
Bits per symbol for one letter symbols: 2.2882
Entropy for two letter symbols: 1.9927064525511913
Bits per symbol for two letter symbols: 7.5697


*Note*
We particularly struggled with the two letter symbols. Our implementation of one letter symbols, while very efficient, was difficult to adapt to two letter symbols. However, the encoding and decoding for the two letter symbols does accurately work.

The code for Huffman.java and Node.java are both based off code taken from:
 * Huffman Algorithm for the DEI's Programming Contest, 2004
 * (c) Paulo Marques, 2004.
 * pmarques@dei.uc.pt

Changes were heavily made to both in order to accommodate the two letter symbols.