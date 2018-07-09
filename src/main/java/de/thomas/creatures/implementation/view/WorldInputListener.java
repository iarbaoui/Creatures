package de.thomas.creatures.implementation.view;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.thomas.creatures.implementation.controller.WorldController;

public class WorldInputListener implements KeyListener, MouseListener, MouseWheelListener  {
	private WorldController controller;
	private WorldView view;
	private final Set<Integer> pressed = new HashSet<Integer>();
	private boolean rightButtonPressed = false;
	private boolean leftButtonPressed = false;
	private Point lastPosition = MouseInfo.getPointerInfo().getLocation();

	public WorldInputListener(WorldController controller, WorldView view) {
		this.controller = controller;
		this.view = view;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		pressed.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressed.remove(e.getKeyCode());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		view.requestFocus();

		if (e.getButton() == MouseEvent.BUTTON1) {
			leftButtonPressed = true;
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			rightButtonPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftButtonPressed = false;
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			rightButtonPressed = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int zoomFactor = view.getZoomFactor();

		if (zoomFactor + e.getWheelRotation() > 0) {
			view.setZoomFactor(zoomFactor + e.getWheelRotation());

			if (e.getWheelRotation() > 0) {
				view.setOffsetX(view.getOffsetX() + (int) ((view.getSize().width) * 0.8) / 2);
				view.setOffsetY(view.getOffsetY() + view.getSize().height / 2);
			}
		}
	}

	public void handlePressedKeys() {
		Iterator<Integer> iterator = pressed.iterator();

		while (iterator.hasNext()) {
			int keyCode = iterator.next();

			if (keyCode == KeyEvent.VK_LEFT) {
				changeOffsetX(5);
			}
			else if (keyCode == KeyEvent.VK_RIGHT) {
				changeOffsetX(-5);
			}
			else if (keyCode == KeyEvent.VK_UP) {
				changeOffsetY(5);
			}
			else if (keyCode == KeyEvent.VK_DOWN) {
				changeOffsetY(-5);
			}
			else if (keyCode == KeyEvent.VK_ADD) {
				controller.changeSpeed(1);
				iterator.remove();
			}
			else if (keyCode == KeyEvent.VK_SUBTRACT) {
				controller.changeSpeed(-1);
				iterator.remove();
			}
		}
	}

	public void handlePressedMouseButtons(WorldView view) {
		double deltaX = MouseInfo.getPointerInfo().getLocation().x - lastPosition.x;
		double deltaY = MouseInfo.getPointerInfo().getLocation().y - lastPosition.y;

		if (rightButtonPressed || leftButtonPressed) {
			if (Math.abs(deltaX) > 0) {
				changeOffsetX(((int) deltaX) * view.getZoomFactor());
			}

			if (Math.abs(deltaY) > 0) {
				changeOffsetY(((int) deltaY) * view.getZoomFactor());
			}
		}

		lastPosition = MouseInfo.getPointerInfo().getLocation();
	}


	private void changeOffsetX(int offsetChange) {
		int offsetX = view.getOffsetX();
		view.setOffsetX(offsetX + offsetChange);
	}

	private void changeOffsetY(int offsetChange) {
		int offsetY = view.getOffsetY();
		view.setOffsetY(offsetY + offsetChange);
	}
}
