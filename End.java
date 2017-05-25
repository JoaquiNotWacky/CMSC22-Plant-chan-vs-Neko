public class End extends Sprite{
	private Lawn lawn;
	public End(Lawn lawn){
		super(283, 217, "Uwin2.png");
		this.lawn = lawn;
	}

	public void show(){
		if(this.lawn.getIsWin())this.loadImage("Uwin2.png");
		else this.loadImage("ULOSE.png");
	}
}
