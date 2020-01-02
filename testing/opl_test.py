import random
import math

#change file
file_input = "opl_test2.txt"
f = open(file_input,"w+")

file_output = file_input[:-4] + "_results" + ".txt"
g = open(file_output,"w+")

#change any
seat_count = 15
ballot_count = 500000

#change count AND list
candidate_count = 10
candidates = ['A','B','C','D','E','F','G','H','I','J']
for i in range(candidate_count):
    candidates[i] = 'candidate_' + candidates[i]

#change party list
parties = ['D','R']
for i in range(len(parties)):
    parties[i] = 'party_' + parties[i]

candidate_parties = ['']*candidate_count
ballots = [0]*candidate_count

f.write("OPL\n")
f.write("%d\n" % seat_count)
f.write("%d\n" % ballot_count)
f.write("%d\n" % candidate_count)
for i in range(candidate_count):
    string = '['
    string += candidates[i]
    string += ','
    party = random.choice(parties)
    candidate_parties[i] = party
    string += party
    string += ']'
    f.write("%s\n" % string)
for i in range(ballot_count):
    string = ''
    first = random.randint(0,candidate_count-1)
    string += ','*first
    string += '1'
    string += ',' * (candidate_count-1-first)
    f.write("%s\n" % string)
    ballots[first] += 1
f.close()

party_ballots = [0]*len(parties)
totals = sorted(list(zip(candidates, candidate_parties, ballots)), key = lambda x: (x[1], x[2]))[::-1]
for i in range(candidate_count):
    party_ballots[parties.index(totals[i][1])] += totals[i][2]
party_totals = sorted(list(zip(parties, party_ballots)), key = lambda x: x[1])[::-1]

temp = totals[0][1]
g.write("----- candidate order -----\n")
for i in range(candidate_count):
    if totals[i][1] != temp:
        g.write("\n")
    g.write("%s (votes: %s): %s\n" % (totals[i][1], totals[i][2], totals[i][0]))
    temp = totals[i][1]
g.write("\n----- vote totals -----\n")
for i in range(len(parties)):
    g.write("%s: %s\n" % (party_totals[i][0], party_totals[i][1]))

g.write("\n----- seat allocation info -----\n")
g.write("votes: %d\n" % ballot_count)
g.write("seats: %d\n" % seat_count)
quota = math.floor(ballot_count/seat_count)
g.write("votes needed for 1 seat: %d\n\n" % quota)
party_seats = [0]*len(parties)
parties, party_ballots = zip(*party_totals)
parties = list(parties)
party_ballots = list(party_ballots)
for i in range(len(parties)):
    num = party_ballots[i]
    seats = math.floor(num/quota)
    party_seats[i] += seats
    party_ballots[i] = party_ballots[i] % quota
    seat_count -= seats
remaining = sorted(list(zip(parties, party_ballots, party_seats)), key = lambda x: x[1])[::-1]
parties, party_ballots, party_seats = zip(*remaining)
parties = list(parties)
party_ballots = list(party_ballots)
party_seats = list(party_seats)
i = 1
while seat_count != 0:
    party_seats[i-1] += 1
    i += 1
    i = i % len(parties)
    seat_count -= 1
final = sorted(list(zip(parties, party_seats)), key = lambda x: x[1])[::-1]
g.write("----- seats won -----\n")
for i in range(len(parties)):
    g.write("%s: %d\n" % (final[i][0], final[i][1]))
g.close()
