package user;

import game.card.Card;

public class Player {
	// Variables
	private String playerName;
	private int currentMoney;
	private int playerPosition;
	private boolean isCurrent;
	private boolean isBigBlind;
	private boolean isSmallBlind;
	private boolean folded;
	private Card[] cards;
	private int amountCalled;


	// money attribute, chip amount, name, file io stats, profile, fold,call, raise methods.
	public Player(String playerName, int currentMoney, int playerPosition, boolean isCurrent,
				  boolean isBigBlind, boolean isSmallBlind, boolean folded, Card[] cards) {
		this.playerName = playerName;
		this.currentMoney = currentMoney;
		this.playerPosition = playerPosition;
		this.isCurrent = isCurrent;
		this.isBigBlind = isBigBlind;
		this.isSmallBlind = isSmallBlind;
		this.folded = folded;
		this.cards = cards;
		amountCalled = 0;
	}

	public String toString(){
		return playerName + "," + String.valueOf(currentMoney) + "," + String.valueOf(playerPosition) + "," +
		String.valueOf(isCurrent) + "," + String.valueOf(isBigBlind) + "," + String.valueOf(isSmallBlind) + "," +
		String.valueOf(folded) +"," + cards[0].toString() + "," + cards[1].toString();
	}

	public void setAmountCalled(int amount){
		this.amountCalled = amount;
	}

	public void setMoney(int amount){
		this.currentMoney = amount;
	}

	public Card[] getCards(){
		return cards;
	}

	public boolean hasFolded(){
		return folded;
	}

	public void setFolded(boolean state){
		folded = state;
	}

	/**
	 * @return the isCurrent
	 */
	public boolean isCurrent() {
		return isCurrent;
	}

	/**
	 * @param isCurrent the isCurrent to set
	 */
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	/**
	 * @return the isBigBlind
	 */
	public boolean isBigBlind() {
		return isBigBlind;
	}

	/**
	 * @param isBigBlind the isBigBlind to set
	 */
	public void setBigBlind(boolean isBigBlind, int bigBlind) {
		this.isBigBlind = isBigBlind;
        this.currentMoney -= bigBlind;
        this.amountCalled = bigBlind;
	}

	/**
	 * @return the isSmallBlind
	 */
	public boolean isSmallBlind() {
		return isSmallBlind;
	}

	/**
	 * @param isSmallBlind the isSmallBlind to set
	 */
	public void setSmallBlind(boolean isSmallBlind, int smallBlind) {
		this.isSmallBlind = isSmallBlind;
        this.currentMoney -= smallBlind;
        this.amountCalled = smallBlind;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * @return the currentMoney
	 */
	public int getCurrentMoney() {
		return currentMoney;
	}

	/**
	 * @param currentMoney the currentMoney to set
	 */
	public void editCurrentMoney(int currentMoney) {
		this.currentMoney += currentMoney;
	}

	public void editAmountCalled(int wager){
		this.amountCalled += wager;
	}

	public int getAmountCalled(){
		return amountCalled;
	}

	/**
	 * @return the playerPosition
	 */
	public int getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * @param playerPosition the playerPosition to set
	 */
	public void setPlayerPosition(int playerPosition) {
		this.playerPosition = playerPosition;
	}
}