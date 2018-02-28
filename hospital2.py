import random

class Patient(object):
    def __init__(self, id, name, allergies):
        self.id = id
        self.name = name
        self.allergies = allergies
        self.bed = 0
    def __repr__(self):
        print "Patient ID: {}, Name: {}, Allergies: {}, Bed Number: {}".format(self.id, self.name, self.allergies, self.bed)
        return self

patient1 = Patient(1, "Stacy", "Milk")
patient2 = Patient(2, "Cenk", "Penicillan")
patient3 = Patient(3, "Jeeves", "Canteloupe")
patient4 = Patient(4, "Jane", "Hayfever")
patient5 = Patient(5, "Caroline", "None")
patient6 = Patient(6, "Stavos", "Peanuts")

# patient1.__repr__()

class Hospital(object):
    def __init__(self, name, capacity):
        self.patients = []
        self.name = name
        self.capacity = capacity
    def __repr__(self):
        print "{} Hospital - Capacity: {}, Current Number of patients: {}".format(self.name, self.capacity, len(self.patients))
        return self
    def admit(self, patient):
        if len(self.patients) < self.capacity: 
            count = 0
            patient.bed = random.randint(1, self.capacity)
            for x in self.patients: 
                if x.bed == patient.bed:
                    patient.bed = random.randint(1, self.capacity)
                count += 1
            self.patients.append(patient)
            stats = "Admitting {} to {} Hospital. Bed Number: {}.".format(patient.name, self.name, patient.bed)
            print stats
        else:
            print "We are at capacity and cannot admit any new patients."
            print "{} Hospital's capacity is {} and currently has {} patients".format(self.name, self.capacity, len(self.patients))
        return self
    def discharge(self, patient):
        x = 0
        for x in self.patients:
            while x < len(self.patients)-1:
                if self.patients[x] is not patient:
                    x += 1
                else:
                    self.patients[x] = self.patients[x+1]
                    x += 1
            self.patients.pop()
            numpat = len(self.patients)
        print "Discharging {}. Bed Number {} is now empty.".format(patient.name, patient.bed, numpat)
        return self

newhosp = Hospital("Sibley", 4)

newhosp.admit(patient1).admit(patient2).discharge(patient2).admit(patient3).admit(patient4).admit(patient5).discharge(patient1).admit(patient6).admit(patient2).admit(patient1).discharge(patient3).discharge(patient4).admit(patient1).__repr__()
