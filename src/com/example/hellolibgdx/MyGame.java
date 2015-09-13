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
	// ����һ��Stage����
	Stage stage;
	// ����һ���ı���������
	TextField textField;
	// ����һ���ı�������Style����
	TextFieldStyle style;
	/**
	 * ����BitmapFont�������ڻ����ı�������е�������Ϣ
	 */
	BitmapFont font;
	// �����ͼ����
	TextureAtlas atlas;
	// ����������Ӧ��Region����
	TextureRegion cursorRegion;
	// ����ѡ��Ч������Ӧ��Region����
	TextureRegion selectionRegion;
	// �����ı�����򱳾�����Ӧ��Region����
	TextureRegion bgRegion;
	// ����������Ӧ��Drawable����
	TextureRegionDrawable cursorDrawable;
	// ����ѡ��Ч������Ӧ��Drawable����
	TextureRegionDrawable selectionDrawable;
	// �����ı�����򱳾�����Ӧ��Drawable����
	TextureRegionDrawable bgRegionDrawable;
	// ���ڱ�����ԴAtlas�ļ�
	TextureAtlas bgAtlas;
	// ����ͼƬ
	Image bbgImage;
	// ������������Դ��Region����
	TextureRegion bbgRegion;

	@Override
	public void create() {
		// ����һ��Stage����
		stage = new Stage(480, 800, false);
		// atlas����ĳ�ʼ��
		bgAtlas = new TextureAtlas(Gdx.files.internal("data/movebg.atlas"));
		// ���������ı�����Region����ĳ�ʼ��
		bbgRegion = bgAtlas.findRegion("movebg");
		// Image����ĳ�ʼ��
		bbgImage = new Image(bbgRegion);
		// ����ͼƬ�Ĵ�С
		bbgImage.setSize(480, 800);
		// ��ʼ���������ı��������Դ�ĺ�ͼ����
		atlas = new TextureAtlas(Gdx.files.internal("data/textfield.atlas"));
		// ��ʼ���ı�����򱳾�����Ӧ��Region����
		bgRegion = atlas.findRegion("bg");
		// ��ʼ���ı������������Ӧ��Region����
		cursorRegion = atlas.findRegion("guangbiao");
		// ��ʼ���ı������ѡ��Ч������Ӧ��Region����
		selectionRegion = atlas.findRegion("selection");
		// ����ѡ��Ч���ĸ߶�
		selectionRegion.setRegionHeight(40);
		// ��ʼ���ı�����򱳾�����Ӧ��Drawble����
		bgRegionDrawable = new TextureRegionDrawable(bgRegion);
		// ��ʼ���ı������������Ӧ��Drawble����
		cursorDrawable = new TextureRegionDrawable(cursorRegion);
		// ��ʼ���ı������ѡ��Ч������Ӧ��Drawable����
		selectionDrawable = new TextureRegionDrawable(selectionRegion);
		// ��ʼ���ı������������Ҫ�õ���BitmapFont����
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), false);
		// ��������Ĵ�С
		font.setScale(0.8f);
		// ��ʼ���ı�������Style����
		style = new TextFieldStyle(font, Color.ORANGE, cursorDrawable,
				selectionDrawable, bgRegionDrawable);
		// ��ʼ���ı������
		textField = new TextField("", style);
		// �����ı������Ŀ��
		textField.setWidth(480);
		// �����ı�������λ��
		textField.setPosition(0, 100);
		// ���ı��������Ӽ�����
		addListenerOnTextFiledToGetMessage1();
		// ��������ӵ���̨
		stage.addActor(bbgImage);
		// ���ı�����ӵ���̨
		stage.addActor(textField);
		// ��Stage������������������¼�
		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * ���ı��������Ӽ����¼��ĵڶ��ַ�ʽ
	 */
	public void addListenerOnTextFiledToGetMessage2() {
		/**
		 * ͨ��addListener���ı��������Ӽ�����
		 */
		textField.addListener(new InputListener() {
			/**
			 * ����ָ����ʱִ��
			 */
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}
			/**
			 * ��ָ�����ʱ��ִ��
			 */
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				/**
				 *  textField.getMessageText():��ȡ�û�������ı���Ϣ
				 */
				System.out.println("----------------->�û��ո������������: "
						+ textField.getMessageText());
				/**
				 * textField.getText():��ȡ�û�������ı���Ϣ
				 */
				System.out.println("----------------->�û��ո������������: "
						+ textField.getText());
			}
		});
	}

	/**
	 * ���ı��������Ӽ����¼��ĵ�һ�ַ�ʽ
	 */
	public void addListenerOnTextFiledToGetMessage1() {
		/**
		 * ͨ��setTextFieldListener�ķ�ʽ���ı��������Ӽ�����
		 */
		textField.setTextFieldListener(new TextFieldListener() {
			/**
			 * ���㰴�°�����ʱ��ִ��
			 */
			@Override
			public void keyTyped(TextField textField, char key) {
				/**
				 *  textField.getMessageText():��ȡ�û�������ı���Ϣ
				 */
				System.out.println("----------------->�û��ո������������: "
						+ textField.getMessageText());
				/**
				 * textField.getText():��ȡ�û�������ı���Ϣ
				 */
				System.out.println("----------------->�û��ո������������: "
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
		Gdx.gl.glClearColor(1, 1, 1, 1);// ���ñ���Ϊ��ɫ
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);// ����

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
