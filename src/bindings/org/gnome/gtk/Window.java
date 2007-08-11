/*
 * Window.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Event;
import org.gnome.gdk.Gravity;
import org.gnome.gdk.Screen;

/**
 * The top level Widget that contains other Widgets. Typical examples are
 * application windows, dialog boxes, and popup menus.
 * 
 * @author Andrew Cowie
 * @author Srichand Pendyala
 * @author Sebastian Mancke
 * @since 4.0.0
 */
public class Window extends Bin
{
    protected Window(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Window.
     */
    public Window() {
        super(GtkWindow.createWindow(WindowType.TOPLEVEL));
    }

    /**
     * Create a new Window of the specified type. In general you don't need to
     * use this; see the comments in
     * {@link org.gnome.gtk.WindowType WindowType}; in particular,
     * {@link org.gnome.gtk.WindowType#POPUP POPUP} is <b>not</b> for dialog
     * windows!
     * 
     * @since 4.0.0
     */
    public Window(WindowType type) {
        super(GtkWindow.createWindow(type));
    }

    /**
     * Sets the title that will be displayed in the Window's title bar.
     * <p>
     * The title of a Window is an important usability factor. It should help
     * the user distinguish this Window from others they may have open - and
     * that gets tough when many, many applications are running. The key is to
     * get the most relevant information is first. Examples of good titles
     * are:
     * <ul>
     * <li><b>Invoice.odt</b>
     * <li><b>Invoice.odt - OpenOffice</b>
     * <li><b>andrew@procyon:~/src</b>
     * <li><b>Audio Configuration</b>
     * </ul>
     * 
     * <p>
     * This is important because the list of Windows titles the user is
     * looking at may have been truncated with the result that you can't tell
     * the difference between different Windows of the same application. For
     * example, these are no good if you can only see the first 20 characters
     * of the title:
     * 
     * <ul>
     * <li><b>OpenOffice 2.0.4 brought to yo</b>u by the letter B! -
     * Invoice.odt
     * <li><b>OpenOffice 2.0.4 brought to yo</b>u by the letter B! -
     * LoveLetter.odt
     * </ul>
     * 
     * <p>
     * Don't forget that Windows also have an icon, and that icon will show in
     * the list too, so you don't even really need the application name -
     * leaving more room for the details that help identify this Window
     * uniquely.
     * 
     * @see <a
     *      href="http://developer.gnome.org/projects/gup/hig/2.0/windows-primary.html#primary-window-titles">GNOME
     *      Human Interface Guidelines</a>
     * @since 4.0.0
     */
    public void setTitle(String title) {
        GtkWindow.setTitle(this, title);
    }

    /**
     * By default, Windows are decorated with a title bar,
     * minimize/maximize/close buttons, a border, resize handles, etc. This
     * isn't done by your program, though - it's automatically by the window
     * manager which is a part of your desktop. Some window managers allow GTK
     * to disable these decorations, creating a borderless window. If you set
     * the decorated property to <code>false</code> using this method, GTK
     * will do its best to convince the window manager not to decorate the
     * Window.
     * 
     * <p>
     * <ul>
     * <li>You will have no problem creating undecorated Windows on a GNOME
     * desktop.
     * <li>Apparently, turning off decorations will not work if the Window is
     * already visible on some systems. So if you're going to use
     * <code>setDecorated(false)</code>, call it before invoking
     * {@link Widget#show() show()} on the Window.
     * </ul>
     * 
     * @since 4.0.0
     */
    public void setDecorated(boolean decorated) {
        GtkWindow.setDecorated(this, decorated);
    }

    /**
     * Sets the default size of a Window. If the Window's "natural" size (the
     * size request resulting from the aggregate requests of all the Widgets
     * contained in this Window) is larger than the default, the default will
     * be ignored. The default size of a Window only affects the first time a
     * Window is shown; if a Window is hidden and re-shown, it will remember
     * the size it had prior to hiding, rather than using the default size.
     * 
     * <p>
     * Depending on your needs, {@link #resize() resize()} could be more
     * appropriate, especially if the Window is already realized . resize()
     * changes the current size of the Window, rather than the size to be used
     * on initial display which is what this method is for.
     * 
     * <p>
     * Incidentally, Windows can't be 0x0; the minimum size is 1x1.
     * 
     * @param width
     *            The default minimum width you'd like to set. A value of 0
     *            will be silently bumped to 1. A value of -1 will unset any
     *            previous default width setting.
     * @param height
     *            Same.
     * @since 4.0.1
     */
    public void setDefaultSize(int width, int height) {
        GtkWindow.setDefaultSize(this, width, height);
    }

    /**
     * Set a new constraint for the position that the Window will be rendered
     * on the screen. Note that this is not always honoured by window
     * managers, but it's a good start.
     * 
     * <p>
     * Somewhat unusually, if the new value for <code>position</code> is
     * {@link WindowPosition#CENTER_ALWAYS CENTER_ALWAYS}, then this call
     * will also result in the Window being moved to the new centered
     * position.
     * 
     * @since 4.0.3
     * @see WindowPosition
     */
    public void setPosition(WindowPosition position) {
        GtkWindow.setPosition(this, position);
    }

    /**
     * Ask the window manager to place the Window in the fullscreen state.
     * Note that you shouldn't assume the Window is definitely fullscreen
     * afterwards, because other entities (e.g. the user or window manager)
     * could unfullscreen it again, and not all window managers honour
     * requests to fullscreen windows. Be prepared for these eventualities.
     * 
     * @since 4.0.3
     */
    /*
     * Remap this as setFullscreen(boolean)? At the moment the answer is no:
     * a) for algorithmic mapping reasons, and b) to correspond to other "take
     * action" methods like clicked() in Button.
     */
    public void fullscreen() {
        GtkWindow.fullscreen(this);
    }

    /**
     * Asks to toggle off the fullscreen state for the Window. Note that you
     * should not assume the Window is definitely not fullscreen afterwards,
     * because other entities (e.g. the user or window manager) could
     * fullscreen it again, and not all window managers honour requests to
     * deactivate fullscreen mode.
     * 
     * @since 4.0.3
     */
    public void unfullscreen() {
        GtkWindow.unfullscreen(this);
    }

    /**
     * This signal arises when a user tries to close a top level window. As
     * you would expect, the default handler for this signal destroys the
     * Window.
     * 
     * <p>
     * If you want to prevent a Window from being closed, connect this signal,
     * and return <code>true</code>. Often the reason to do this is to pop
     * up a notification Dialog, for example asking you if you want to save an
     * unsaved document. Another technique is reusing a Window: rather than
     * going to all the trouble to create this Window again, you can just
     * temporarily hide it by calling {@link Widget#hide() Widget's hide()}.
     * 
     * <p>
     * <i> This signal is actually "delete-event" which lives on GtkWidget.
     * That, however, is for implementation reasons in GTK because all the
     * GdkEvents go to GtkWidget even though this particular signal only has
     * to do with Windows. So, we expose it here. </i>
     * 
     * @author Andrew Cowie
     * @author Devdas Bhagat
     * @since 4.0.0
     */
    public interface DELETE_EVENT extends GtkWidget.DELETE_EVENT
    {
        public boolean onDeleteEvent(Widget source, Event event);
    }

    public void connect(DELETE_EVENT handler) {
        GtkWidget.connect(this, handler);
    }

    /**
     * Request that the Window be moved to the specified co-ordinates. As with
     * other Window operations, the window manager running on the display may
     * or may not service the request; in particular sometimes find that
     * initial placement is overridden by the window manager.
     * 
     * <p>
     * <code>x</code> and <code>y</code> are in pixels.
     * 
     * <p>
     * Chances are
     * {@link org.gnome.gtk.Window#setPosition(org.gnome.gtk.WindowPosition) setPosition()}
     * will do what you want more easily than manually moving the Window.
     * 
     * <p>
     * <i>The co-ordinates <code>x</code>, <code>y</code> are with
     * respect to the reference point specified by the "gravity" in effect for
     * this Window; since the default is</i>
     * {@link Gravity#NORTH_WEST NORTH_WEST}<i>, x and y mean what you want
     * them to: horizontal and vertical distance of the top-left corner of the
     * Window from the top-left corner, respectively.</i>
     * 
     * @since 4.0.4
     */
    public void move(int x, int y) {
        GtkWindow.move(this, x, y);
    }

    /**
     * Set the interpretation of co-ordinates passed to
     * {@link #move(int, int)} and returned by
     * {@link #getPositionX() getPosition()}.
     * 
     * <p>
     * <i>The window manager specification has long been notorious for not
     * actually being a spec; it's more a collection of guesses, assumptions,
     * and outright lies. Unsurprisingly, then, even a window manager that
     * wants to do the right thing can't get it right because there isn't
     * actually a correct answer. Gravity is a case in point, apparently. You
     * might as well consider that things are broken and stick with the
     * default, NORTH_WEST. If you insist on using this anyway, keep in mind
     * that users may experience widely varying results.</i>
     * 
     * @since 4.0.4
     */
    public void setGravity(Gravity gravity) {
        GtkWindow.setGravity(this, gravity);
    }

    /**
     * Get the position of the Window frame (x co-ordinate). This is relative
     * to the Window's gravity setting; since the default is
     * {@link Gravity#NORTH_WEST} this usually means horizontal distance from
     * the top-left corner, which is the normal usage on X displays.
     * 
     * <p>
     * <i>Apparently this is not entirely reliable; X itself does not provide
     * an authoritative means to determine the dimensions of any decorations
     * the window manager might have applied around a Window, and so GTK does
     * its best to guess the necessary adjustments that "should work with sane
     * window managers". We leave it as an exercise to the reader to define
     * sanity.</i>
     * 
     * @since 4.0.4
     */
    public int getPositionX() {
        int[] x = new int[1];
        int[] y = new int[1];

        GtkWindow.getPosition(this, x, y);

        return x[0];
    }

    /**
     * Get the position of the Window frame (y co-ordinate). See
     * {@link #getPositionX()} for details.
     * 
     * @since 4.0.4
     */
    public int getPositionY() {
        int[] x = new int[1];
        int[] y = new int[1];

        GtkWindow.getPosition(this, x, y);

        return y[0];
    }

    /**
     * Get the Screen that this Window is on.
     */
    public Screen getScreen() {
        return GtkWindow.getScreen(this);
    }

    /**
     * Request that this Window be kept on top of all other windows.
     * 
     * <p>
     * Note that the request to apply the "keep above" state may be overridden
     * or ignored by the window manager. Likewise, the user may toggle this
     * state between the program requesting it and the program subsequently
     * proceeding on the expectation that it is set. As a result you should
     * not write code that assumes this request has been successful.
     * 
     * <p>
     * The window manager specifications are fairly explicit that these
     * settings are a user preference. In particular, "keep above" should not
     * be used as a gimmick to attempt to draw attention to a Window.
     * 
     * <p>
     * Usability note: while it always seems like such a good idea to put your
     * favourite window on top of everything else, in practise this can pale.
     * You will find that your current "favourite" changes frequently, and not
     * being able to rely on the normal window management behaviour to bring
     * whatever you are <i>now</i> working on over top of the Window you have
     * kept above will quickly result in you being annoyed that you can't get
     * rid if it. All the usual arguments against modal windows also apply.
     * 
     * <p>
     * Since a proper window manager like <code>Metacity</code> gives you
     * quick and immediate access to the "keep on top" mode via a right click
     * on the Window's title bar decoration, you really only should need this
     * on the rare occasions when you have turned off decorations. So yes,
     * there are are legitimate uses for this, but they are few and far
     * between.
     * 
     * @param setting
     *            <code>true</code> to request keep above be on,
     *            <code>false</code> to request normal behaviour.
     * @since 4.0.4
     */
    public void setKeepAbove(boolean setting) {
        GtkWindow.setKeepAbove(this, setting);
    }

    /**
     * Request that this Window be behind all other windows showing on the
     * desktop.
     * 
     * <p>
     * You can call this before <code>show()</code>ing a Window, in which
     * case the initial presentation will be behind other windows.
     * 
     * <p>
     * The caveats and notes discussed in
     * {@link #setKeepAbove(boolean) setKeepAbove()} apply here. Once again,
     * while there are legitimate uses for this method, please think about the
     * impact on user's overall desktop experience before employing it.
     * 
     * 
     * @param setting
     *            <code>true</code> to request this Window be kept behind
     *            all other windows on the desktop, <code>false</code> for
     *            normal behaviour.
     * @since 4.0.4
     */
    public void setKeepBelow(boolean setting) {
        GtkWindow.setKeepBelow(this, setting);
    }

    /**
     * Request that the Window be visible on all user workspaces.
     * 
     * <p>
     * Many window managers provide the concept of "workspaces" or "virtual
     * desktops" whereby the user can switch from one to another and use this
     * as a means of organizing their work. Ordinarily, an application's
     * Windows will only show in the workspace they appeared in (or to which
     * they were moved by the user). By calling <code>setStick(true)</code>,
     * the Window will always be visible, regardless of which workspace is
     * switched to. While not all window managers have this capability, in
     * general this will work.
     * 
     * <p>
     * Note that the request to stick may not succeed, or may subsequently be
     * reversed by the user.
     * 
     * <p>
     * <i>Some desktops show a thumbnail of each workspace and when a window
     * is stuck, it will "appear" in each workspace thumbnail. This does not
     * mean there are suddenly four copies of your application running or
     * anything silly like that.</i>
     * 
     * @param <code>true</code> to request the Window be stuck,
     *            <code>false</code> to request a return to the normal
     *            default state.
     * @since 4.0.4
     */
    public void setStick(boolean setting) {
        if (setting) {
            GtkWindow.stick(this);
        } else {
            GtkWindow.unstick(this);
        }
    }
}
