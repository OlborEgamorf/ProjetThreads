class Systeme:

    def __init__(self,flux,taillePlage,vent,tempAir,danger,tempEau,sauveteurs):
        self.fluxMulti = flux # 1 = flux normal à définir, 
        self.taille = taillePlage # taille de la plage, en pixels, tupple (x,y)
        self.meteo = Meteo(vent,tempAir) # état de la météo, influe sur le flux
        self.tempEau = tempEau # température de l'eau
        self.sauveteurs = sauveteurs # nombre de sauveteurs
        self.temps=25200 # 7h du mat

    def malusFlux(self):
        if self.meteo.temperature > 40:
            self.fluxMulti /= 2
        elif self.meteo.temperature > 50:
            self.fluxMulti = 0

        if self.meteo.vent > 50:
            self.fluxMulti *= 0.8
        elif self.meteo.vent > 70:
            self.fluxMulti /= 2
        elif self.meteo.vent > 90:
            self.fluxMulti = 0
        
        

class Meteo:

    def __init__(self,vent:int,temp:int):
        """Vent en km/h, Température en degrés Celcius"""
        self.vent = vent
        self.temperature = temp


