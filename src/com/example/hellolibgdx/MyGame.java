package com.example.hellolibgdx;

import java.nio.channels.spi.SelectorProvider;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MyGame implements ApplicationListener {
	// 定义一个Stage对象
	Stage stage;
	// 定义一个文本输入框对象
	TextField textField;
	// 定义一个文本输入框的Style对象
	TextFieldStyle style;
	/**
	 * 定义BitmapFont对象，用于绘制文本输入框中的文字信息
	 */
	BitmapFont font;
	// 定义合图对象
	TextureAtlas atlas;
	// 定义光标所对应的Region对象
	TextureRegion cursorRegion;
	// 定义选中效果所对应的Region对象
	TextureRegion selectionRegion;
	// 定义文本输入框背景所对应的Region对象
	TextureRegion bgRegion;
	// 定义光标所对应的Drawable对象
	TextureRegionDrawable cursorDrawable;
	// 定义选中效果所对应的Drawable对象
	TextureRegionDrawable selectionDrawable;
	// 定义文本输入框背景所对应的Drawable对象
	TextureRegionDrawable bgRegionDrawable;
	// 用于背景资源Atlas文件
	TextureAtlas bgAtlas;
	// 背景图片
	Image bbgImage;
	// 用作背景的资源的Region对象
	TextureRegion bbgRegion;

	@Override
	public void create() {
		// 定义一个Stage对象
		stage = new Stage(480, 800, false);
		// atlas对象的初始化
		bgAtlas = new TextureAtlas(Gdx.files.internal("data/movebg.atlas"));
		// 整个场景的背景的Region对象的初始化
		bbgRegion = bgAtlas.findRegion("movebg");
		// Image对象的初始化
		bbgImage = new Image(bbgRegion);
		// 设置图片的大小
		bbgImage.setSize(480, 800);
		// 初始化保存了文本输入框资源的和图对象
		atlas = new TextureAtlas(Gdx.files.internal("data/textfield.atlas"));
		// 初始化文本输入框背景所对应的Region对象
		bgRegion = atlas.findRegion("bg");
		// 初始化文本输入框光标所对应的Region对象
		cursorRegion = atlas.findRegion("guangbiao");
		// 初始化文本输入框选中效果所对应的Region对象
		selectionRegion = atlas.findRegion("selection");
		// 设置选中效果的高度
		selectionRegion.setRegionHeight(40);
		// 初始化文本输入框背景所对应的Drawble对象
		bgRegionDrawable = new TextureRegionDrawable(bgRegion);
		// 初始化文本输入框光标所对应的Drawble对象
		cursorDrawable = new TextureRegionDrawable(cursorRegion);
		// 初始化文本输入框选中效果所对应的Drawable对象
		selectionDrawable = new TextureRegionDrawable(selectionRegion);
		// 初始化文本输入框众所需要用到的BitmapFont对象
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), false);
		// 设置字体的大小
		font.setScale(0.8f);
		// 初始化文本输入框的Style对象
		style = new TextFieldStyle(font, Color.ORANGE, cursorDrawable,
				selectionDrawable, bgRegionDrawable);
		// 初始化文本输入框
		textField = new TextField("", style);
		// 设置文本输入框的宽度
		textField.setWidth(480);
		// 设置文本输入框的位置
		textField.setPosition(0, 100);
		// 给文本输入框添加监听器
		addListenerOnTextFiledToGetMessage1();
		// 将背景添加到舞台
		stage.addActor(bbgImage);
		// 将文本框添加到舞台
		stage.addActor(textField);
		// 用Stage对象来处理输入输出事件
		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * 给文本输入框添加监听事件的第二种方式
	 */
	public void addListenerOnTextFiledToGetMessage2() {
		/**
		 * 通过addListener给文本输入框添加监听器
		 */
		textField.addListener(new InputListener() {
			/**
			 * 当手指按下时执行
			 */
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}
			/**
			 * 手指弹起的时候执行
			 */
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				/**
				 *  textField.getMessageText():获取用户输入的文本信息
				 */
				System.out.println("----------------->用户刚刚输入的内容是: "
						+ textField.getMessageText());
				/**
				 * textField.getText():获取用户输入的文本信息
				 */
				System.out.println("----------------->用户刚刚输入的内容是: "
						+ textField.getText());
			}
		});
	}

	/**
	 * 给文本输入框添加监听事件的第一种方式
	 */
	public void addListenerOnTextFiledToGetMessage1() {
		/**
		 * 通过setTextFieldListener的方式给文本输入框添加监听器
		 */
		textField.setTextFieldListener(new TextFieldListener() {
			/**
			 * 当你按下按键的时候执行
			 */
			@Override
			public void keyTyped(TextField textField, char key) {
				/**
				 *  textField.getMessageText():获取用户输入的文本信息
				 */
				System.out.println("----------------->用户刚刚输入的内容是: "
						+ textField.getMessageText());
				/**
				 * textField.getText():获取用户输入的文本信息
				 */
				System.out.println("----------------->用户刚刚输入的内容是: "
						+ textField.getText());
			}
		});
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);// 设置背景为白色
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);// 清屏

		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
