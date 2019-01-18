
package grade12cpt;

public class MeleeWeapon extends Weapon{
  
    // Gives two unique items and updates with unique stats of the super class (Weapon)
    // Could be done in Weapon Class, but this is better organized
    
    static class Sword extends MeleeWeapon {

        public Sword() {
            spriteSheet = "src/images/sword1.png";
            damage = 30;
        }
        
    }
    
    static class Axe extends MeleeWeapon {
        
        public Axe() {
            spriteSheet = "src/images/axe.png";
            damage = 20;
        }
    }

    
    
    

}





