class Call(object):
    def __init__(self, id, name, phone, timeof, reason):
        self.id = id
        self.name = name
        self.phone = phone
        self.timeof = timeof
        self.reason = reason
    def display(self): 
        print "Caller ID: {}, Caller Name: {}, Phone Number: {}, Time of call: {}, Reason for call: {}".format(self.id, self.name, self.phone, self.timeof, self.reason)

caller1 = Call(1, "Stacy", 2025551234, "5pm", "help")
caller2 = Call(2, "Cenk", 2025555678, "5:03pm", "bored")
caller3 = Call(3, "Jeeves", 2025559012, "8pm", "homework question")
caller4 = Call(4, "Jane", 2025554321, "8:30pm", "hi mom")
caller5 = Call(5, "Caroline", 2025558765, "9:47pm", "tv show canceled")
caller6 = Call(6, "Stavos", 2025552109, "11pm", "cat woke me up")

# caller1.display()
# caller2.display()
# caller3.display()

class CallCenter(object):
    def __init__(self):
        self.calls = []
        self.queue = 0
    def add(self, caller):
        self.calls.append(caller)
        self.queue += 1
        for call in self.calls:
            stats = "{} - {}".format(call.name, call.phone)
        return self
    def remove(self):
        x = 0
        while x < len(self.calls)-1:
            temp = self.calls[x]
            self.calls[x] = self.calls[x+1]
            self.calls[x+1] = temp
            x += 1
        self.calls.pop()
        self.queue -= 1
        return self
    def info(self):
        print "{} call(s) in queue".format(self.queue)
        for call in self.calls:
            call.display()

newcenter = CallCenter()

newcenter.add(caller1).add(caller2).add(caller3).remove().add(caller4).remove().add(caller5).remove().add(caller6).remove().info()